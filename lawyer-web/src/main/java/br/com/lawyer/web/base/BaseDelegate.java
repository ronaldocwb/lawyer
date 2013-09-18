package br.com.lawyer.web.base;

import br.com.lawyer.core.base.IUID;
import br.com.lawyer.web.vo.BaseVO;
import org.apache.commons.beanutils.ConstructorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Deividi Cavarzan
 * @since: 10/09/13
 */
@SuppressWarnings("unchecked")
public abstract class BaseDelegate<T extends IUID<?>, V extends BaseVO> {


    /**
     * Converte uma entidade para um VO.
     *
     * @param object a entidade a ser convertida.
     * @return o VO da entidade ou <tt>null</tt> caso não seja possível converter.
     * @see {@link #getVO(List)}
     */
    protected V getVO(T object) {
        if (object == null) {
            return null;
        }

        V vo = null;

        try {
            Class<V> clazz = getClassVO();
            vo = (V) ConstructorUtils.invokeConstructor(clazz, object);
        } catch (Exception e) {
            e.printStackTrace(); //"Não foi possível converter o objeto " + object + " para VO.", e);
        }

        return vo;
    }

    /**
     * Converte uma lista de entidades para uma lista de VOs.
     *
     * @param list a lista de entidades a serem convertidas.
     * @return a lista de VOs.
     * @see #getVO(T)
     */
    protected List<V> getVO(List<T> list) {
        List<V> listVO = new ArrayList();

        for (T t : list) {
            V vo = getVO(t);

            if (vo != null) {
                listVO.add(vo);
            }
        }

        return listVO;
    }

    /**
     * Retorna o Page<ClassVO> com o resultado encontrado para o PageRequest informado.
     * @param page
     * @param pageRequest Page com a lista de resultados da entidade encontrada.
     * @return lista de entidades atraves de um Page.
     */
    protected Page<V> getVO (Page<T> page, PageRequest pageRequest) {
        List<V> contentVO = getVO(page.getContent());
        Page<V> pageResult = new PageImpl<>(contentVO, pageRequest, page.getTotalElements());
        return pageResult;
    }

    /**
     * Retorna a classe que representa o VO.
     *
     * @return a classe que representa o VO.
     * @throws ClassNotFoundException caso a classe do VO não seja encontrada.
     */
    private Class<V> getClassVO() throws ClassNotFoundException {
        Type superclass = this.getClass().getGenericSuperclass();

        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();

            if (typeArguments.length == 2) {
                Type typeVO = typeArguments[1];

                if (typeVO instanceof Class) {
                    Class c = (Class) typeVO;
                    String name = c.getName();

                    return (Class<V>) Class.forName(name);
                }
            }
        }

        return null;
    }

}
