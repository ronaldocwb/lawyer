package br.com.lawyer.web.base;


import br.com.lawyer.core.exception.ParseEntityToVOException;
import br.com.lawyer.core.exception.ParseVOToEntityException;
import br.com.lawyer.web.annotation.IgnoreVOParser;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.LazyInitializationException;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Deividi Cavarzan
 * @since: 10/09/13
 */
public abstract class BaseVO<T> implements Serializable {

    private static final Logger log = Logger.getLogger(BaseVO.class);

    private static final long serialVersionUID = 1L;

    public BaseVO() {
    }

    /**
     * Popula os campos do VO com os valores da entidade.<br>
     * Todos os campos da entidade que no VO correspondem a um campo de um tipo
     * que estende {@link BaseVO} tamb�m ser�o convertidos para o VO
     * correspondente.
     *
     * @param object a entidade cujos valores ser�o copiados para o VO.
     */
    public BaseVO(T object) {
        if (object == null) {
            return;
        }

        Type baseBoType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getRawType();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Object value;

            try {
                value = PropertyUtils.getSimpleProperty(object, field.getName());
            } catch (IllegalAccessException e) {
                throw new ParseEntityToVOException(e);
            } catch (InvocationTargetException e) {
                throw new ParseEntityToVOException(e);
            } catch (NoSuchMethodException e) {
                // Se a entidade n�o possui o campo correspondente ent�o n�o tenta copiar
                continue;
            }
            //TODO lazy
            try {
                if (field.getType().getGenericSuperclass() instanceof ParameterizedType) {
                    Type type = ((ParameterizedType) field.getType().getGenericSuperclass()).getRawType();

                    if (type == baseBoType && value != null) {
                        value = ConstructorUtils.invokeConstructor(field.getType(), value);
                    }
                } else if (value != null && value instanceof List) {
                    value = parseEntityList(field, ((List<?>) value));
                }
            } catch (NoSuchMethodException e) {
                throw new ParseEntityToVOException(e);
            } catch (IllegalAccessException e) {
                throw new ParseEntityToVOException(e);
            } catch (InvocationTargetException e) {
                throw new ParseEntityToVOException(e);
            } catch (InstantiationException e) {
                throw new ParseEntityToVOException(e);
            }

            if (value != null) {
                try {
                    PropertyUtils.setSimpleProperty(this, field.getName(), value);
                } catch (IllegalAccessException e) {
                    throw new ParseEntityToVOException(e);
                } catch (InvocationTargetException e) {
                    throw new ParseEntityToVOException(e);
                } catch (NoSuchMethodException e) {
                    throw new ParseEntityToVOException(e);
                }
            }
        }
    }

    /**
     * Converte o VO para uma entidade.<br>
     * Todos os campos do VO que forem de um tipo que estende {@link BaseVO}
     * tamb�m ser�o convertidos para a entidade correspondente, inclusive
     * quando o campo for uma lista de VOs.
     *
     * @return a entidade representada pelo VO.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public T parse() {
        Class<T> clazz = getClassVO();
        T object;

        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new ParseVOToEntityException(e);
        } catch (IllegalAccessException e) {
            throw new ParseVOToEntityException(e);
        }

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            // Ignora campos que n�o devem ir para a entidade ou que n�o existem na entidade
            if (field.isAnnotationPresent(IgnoreVOParser.class)) {
                continue;
            }

            field.setAccessible(true);
            Object value;

            try {
                value = field.get(this);
            } catch (IllegalArgumentException e) {
                throw new ParseVOToEntityException(e);
            } catch (IllegalAccessException e) {
                throw new ParseVOToEntityException(e);
            }

            if (value instanceof BaseVO) {
                value = ((BaseVO) value).parse();
            } else if (value instanceof List && !((List) value).isEmpty() && ((List) value).get(0) instanceof BaseVO) { // Se for uma lista de VOs
                value = parseVoList((List<BaseVO>) value);
            }

            if (value != null) {
                try {
                    PropertyUtils.setSimpleProperty(object, field.getName(), value);
                } catch (IllegalAccessException e) {
                    throw new ParseVOToEntityException(e);
                } catch (InvocationTargetException e) {
                    throw new ParseVOToEntityException(e);
                } catch (NoSuchMethodException e) {
                    String error = (String.format("Entidade %s n�o possui o campo %s para parse. " +
                            "Considere usar a anota��o %s no campos para descart�-la ou verifique " +
                            "se ela bate com o campo na entidade.",
                            object.getClass().toString(), field.getName(), IgnoreVOParser.class.toString()));
                    throw new ParseVOToEntityException(error, e);
                }
            }
        }

        return object;
    }

    /**
     * Converte uma lista de VOs para uma lista de entidades.
     * Se o par�metro for nulo, ser� retornada uma lista vazia.
     *
     * @param value a lista de VOs.
     * @return a lista de entidades.
     */
    public List<T> parse(List<? extends BaseVO<T>> value) {
        List<T> list = new ArrayList<>();

        if (value == null) {
            return list;
        }

        for (BaseVO<T> vo : value) {
            list.add(vo.parse());
        }

        return list;
    }

    /**
     * Converte uma lista de VOs para uma lista de entidades.
     *
     * @param value a lista de VOs.
     * @return a lista de entidades.
     */
    @SuppressWarnings("rawtypes")
    private List<Object> parseVoList(List<BaseVO> value) {
        List<Object> list = new ArrayList<>();

        for (BaseVO vo : value) {
            list.add(vo.parse());
        }

        return list;
    }

    /**
     * Converte uma lista de entidades para uma lista de VOs.
     * Se o tipo da lista no VO n�o estende {@link BaseVO} ser� retornada a lista passada por par�metro.
     *
     * @param field o campo que representa a lista no VO.
     * @param list a lista de entidades a serem convertidas para VOs.
     * @return a lista de entidades de VOs.
     * @throws NoSuchMethodException caso n�o seja poss�vel instanciar um VO.
     * @throws IllegalAccessException caso n�o seja poss�vel instanciar um VO.
     * @throws java.lang.reflect.InvocationTargetException caso n�o seja poss�vel instanciar um VO.
     * @throws InstantiationException caso n�o seja poss�vel instanciar um VO.
     */
    private List<?> parseEntityList(Field field, List<?> list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            if (list.isEmpty()) {
                return list;
            }
        } catch (LazyInitializationException e) {
            // Se n�o estiver mapeado ou sem @Transaction no metodo pode ocorrer essa exce��o.
            // retorna null nesse caso.
            e.printStackTrace();
            return null;
        }

        if (!(field.getGenericType() instanceof ParameterizedType)) {
            return list;
        }

        Type[] typeArguments = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();

        if (!(typeArguments[0] instanceof Class)) {
            return list;
        }

        Class<?> clazz = (Class<?>) typeArguments[0]; // O tipo da lista no VO

        if (!BaseVO.class.isAssignableFrom(clazz)) { // A lista n�o � de um tipo que estende BaseVO
            return list;
        }

        List<Object> result = new ArrayList<>();

        for (Object entity : list) {
            Object vo = ConstructorUtils.invokeConstructor(clazz, entity); // Constr�i o VO passando a entidade como par�metro
            result.add(vo);
        }

        return result;
    }

    /**
     * Retorna a classe que representa o VO.
     *
     * @return a classe que representa o VO.
     * @throws ClassNotFoundException caso a classe do VO n�o seja encontrada.
     */
    @SuppressWarnings("unchecked")
    private Class<T> getClassVO() {
        Type superclass = this.getClass().getGenericSuperclass();

        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            Type typeVO = typeArguments[0];

            if (typeVO instanceof Class) {
                return (Class<T>) typeVO;
            }
        }

        return null;
    }

}