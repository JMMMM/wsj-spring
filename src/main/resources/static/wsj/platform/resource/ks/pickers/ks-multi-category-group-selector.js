/**
 * ks 分页模块
 * @author Wangyiqun
 * @date 2014-12-29
 */
(function () {


    angular.module('ks.multiCategoryGroupSelector', ['ks', 'ks.cache'])
        .directive('ksMultiCategoryGroupSelector', ["$compile", 'ksCache', function ($compile, ksCache) {

            ksCache.get("categoryGroups", window.webRoot + "/pdm/categorygroups/all.do").then(function (data) {

            });
            return {
                require: 'ngModel',
                scope: {
                    items: '=',
                    mode: '@'//all purchase sell
                },
                restrict: 'E',
                template: ' <ks-multi-selector   ng-model="categoryGroups" items="roles" id-field="groupCode" label-field="groupName"></ks-multi-selector>',
                link: function (scope, element, attrs, ngModel) {

                	var url = "/pdm/categorygroups/all.do"
                	var name = "categoryGroups"
                	if (scope.mode == 'all')
                		url = "/pdm/categorygroups/all.do";
                	else if (scope.mode == 'all-purchase') {
                		url = "/pdm/categorygroups/allPurchase.do";
                		
                		name = "categoryGroups-allPurchase"
                	}
                	else if (scope.mode == 'purchase') {
                		url = "/pdm/categorygroups/picker/purchase.do";
                		
                		name = "categoryGroups-purchase"
                	}
                	else if (scope.mode == 'sell') {
                		url = "/pdm/categorygroups/picker/sell.do";
                		
                		name = "categoryGroups-sell"
                	}
                	
                    var promise = ksCache.get(name, window.webRoot + url).then(function (data) {
                        scope.roles = data;
                    });

                    ngModel.$render = function () {

                        var value = ngModel.$viewValue;
                        console.log('k------>:',value)
                        promise.then(function(data){
                            scope.categoryGroups = value;
                        })
                    }

                    scope.$watch('categoryGroups',function(){
                        ngModel.$setViewValue(scope.categoryGroups);
                    })

                }
            }
        }]);
})();