/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductStatusCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {


    // $scope.pageSize = 2;
    // $scope.maxSize = 5;
    // $scope.numPages = 10;
    ////   $scope.bigTotalItems = 6;
    // $scope.bigCurrentPage = 1;

    $scope.Productstatus = undefined;

    $scope.loadSuppliers = function () {
        Productstatus.GET(function (data) {
            $scope.Productstatus = data;
            //  $scope.bigTotalItems = data.totalRecords;
            //  $scope.numPages = $scope.bigTotalItems/$scope.pageSize;
        });
    };

    $scope.loadSuppliers();

    $scope.showProductStatusInDetail = function (supplier) {
        $location.path('product-status/' + supplier.id);
        //console.log(product);
    };


    //$scope.totalItems = 64;
    //$scope.currentPage = 4;

    //$scope.setPage = function (pageNo) {
    //    $scope.bigCurrentPage = pageNo;
    //    $scope.loadProducts();
    //};
    //
    //$scope.pageChanged = function () {
    //    $scope.loadProducts();
    //};



}]);