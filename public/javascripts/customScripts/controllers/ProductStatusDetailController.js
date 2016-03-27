/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductStatusDetailCtrl',['$scope', '$resource', 'Product', '$stateParams', '$location','Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $stateParams, $location, Supplier, Category, Productstatus) {

    $scope.selectedProductstatus=undefined;
    Productstatus.GET({id:$stateParams.id },function (data) {
        $scope.selectedProductstatus = data;
        console.log($scope.selectedProductstatus);
    });

    $scope.save = function () {
        $scope.selectedProductstatus.update(function () {
            $location.path('/product-status');
        }, function () {

        });
    };

    $scope.delete = function (prod) {
        Productstatus.DELETE({id:prod.id}, function () {
            $location.path('/product-status');
        });
    };



}]);