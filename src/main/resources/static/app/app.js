(function (angular, _, ctx) {

    angular.module('dataset.admin', ['ui.router', 'ui.bootstrap', 'ngTouch', 'ui.select'])
        .constant('AppContext', ctx)
        .config(function ($stateProvider, $urlRouterProvider, AppContext) {
            $urlRouterProvider.otherwise('/documents');

            $stateProvider
                .state('documents', {
                    url: '/documents',
                    templateUrl: AppContext.baseUri + '/documents.html',
                    controller: function (AppContext, $scope, $http) {
                        var ctrl = this;

                        ctrl.buildPageUrl = function (searchTerm) {
                            return AppContext.apiUri + '/documents/search/by-term?searchTerm=' + ctrl.searchTerm + '&page=0&size=100&sort=cluster,title';
                        };

                        ctrl.searchTerm = '';
                        ctrl.currentPage = ctrl.buildPageUrl(ctrl.searchTerm);

                        ctrl.fetchDocuments = function (link) {
                            ctrl.currentPage = link;
                            ctrl.isLoading = true;
                            $http.get(link).then(function (res) {
                                ctrl.documentsresponse = res.data;
                                ctrl.documents_links = res.data._links;
                                ctrl.documents = res.data._embedded.documents;
                            }).finally(function () {
                                ctrl.isLoading = false;
                            });
                        };
                        ctrl.fetchDocuments(ctrl.currentPage);

                        ctrl.toggle = function (document, dataset) {
                            document[dataset] = !document[dataset];
                            ctrl.save(document);
                        };

                        ctrl.delete = function (document) {
                            document.deleted = true;
                            ctrl.save(document);
                        };

                        ctrl.save = function (document) {
                            $http.put(document._links.self.href, document).then(function (res) {
                                ctrl.fetchDocuments(ctrl.currentPage);
                            }, function () {
                                alert('error');
                            });
                        };

                        ctrl.update = _.debounce(ctrl.save, 500);

                        $scope.$watch(function () {
                            return ctrl.searchTerm;
                        }, _.debounce(function (searchTerm) {
                            ctrl.fetchDocuments(ctrl.buildPageUrl(searchTerm));
                        }, 500));
                    },
                    controllerAs: 'ctrl'
                });
        });

})(window.angular, window._, window.ctx);