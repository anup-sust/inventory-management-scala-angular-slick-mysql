/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductRootCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {

    if(!$scope.SuparAdmin&&!$scope.Admin&&!$scope.NormalUser) $location.path('/');

    Supplier.GET(function (data) {
        $scope.inventoryMgmnt.suppliers = data.records;
    });

    Category.GET(function (data) {
        $scope.inventoryMgmnt.categories = data.records;
    });

    Productstatus.GET(function (data) {
        $scope.inventoryMgmnt.productstatus = data.records;
    });

    $scope.big={CurrentPage:1};




}]);