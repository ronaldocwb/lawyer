angular.module('lawyer.atividades.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('atividades.cadastrar', {
            url: '/cadastro',
            controller: 'CadastrarAtividadeController',
            templateUrl: 'atividades/cadastrar/atividades.cadastrar.tpl.html'
        });
    }])

    .controller('CadastrarAtividadeController', ['$scope', '$state', '$log', 'Atividade', 'Municipio', 'notifications', '$http', 'Pessoa', '$modal', 'Setor',
        function ($scope, $state, $log, Atividade, Municipio, notifications, $http, Pessoa, $modal, Setor) {
            $scope.tela = {
                cadastro : true,
                edicao : false
            };
            $scope.atividade = {
                assunto: null,
                dataReferencia: new Date(),
                descricao : null,
                observacao : null,
                tempo : {
                    horas : 0,
                    minutos : 0
                },
                valorHora : '0'
            };

            $scope.dateOptions = {
                'toggle-weeks-text' :'Semanas',
                'close-text' : 'Fechar',
                'starting-day': 1
            };

            $scope.pushAtividadeListagem = function () {
                if ($scope.atividades) {
                    $scope.atividades.content.push($scope.atividade);
                }
            };

            $scope.salvar = function () {
                $scope.atividade.duracao = $scope.atividade.tempo.horas * 60 + $scope.atividade.tempo.minutos;
                $scope.atividade = Atividade.save($scope.atividade, function () {
                    notifications.pushForCurrentRoute('atividade.salva', 'success', {nome: $scope.atividade.nomeFantasia});
                    $scope.pushAtividadeListagem();
                    $state.go('atividades.listar');
                });
            };

            $scope.salvarContinuar = function () {
                $scope.atividade = Atividade.save($scope.atividade, function () {
                    notifications.pushForCurrentRoute('atividade.salva', 'success', {nome: $scope.atividade.nomeFantasia});
                    $scope.pushAtividadeListagem();
                    $scope.atividade = {
                        telefones: [],
                        responsaveis: [],
                        enderecos: []
                    };
                });
            };

            $scope.add = function (key) {
                $scope.atividade[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.atividade[key].splice($index, 1);
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('atividades.listar'));
            };

            $scope.blur = { error: false};
            $scope.onBlurAssunto = function () {
                if (!$scope.atividade.assunto || !$scope.atividade.assunto.uid) {
                    $scope.blur1.error = true;
                } else {
                    $scope.blur.error = false;
                }

            };
        }])

;