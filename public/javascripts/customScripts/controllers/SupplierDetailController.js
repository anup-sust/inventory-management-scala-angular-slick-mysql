/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('SupplierDetailCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', '$stateParams', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus, $stateParams) {


    $scope.selectedSupplier=undefined;
    Supplier.GET({id:$stateParams.id },function (data) {
        $scope.selectedSupplier = data;
      //  console.log($scope.selectedSupplier);
    });

    $scope.save = function () {
        console.log("suppliers");
        $scope.selectedSupplier.update(function () {
            $location.path('/suppliers');
        }, function () {

        });
    };


    $scope.delete = function (prod) {
        Supplier.DELETE({id:prod.id}, function () {
            $location.path('/suppliers');
        });
    };


}]);