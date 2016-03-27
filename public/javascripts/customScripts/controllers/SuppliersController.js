/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('SupplierCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {


   // $scope.pageSize = 2;
   // $scope.maxSize = 5;
   // $scope.numPages = 10;
   ////   $scope.bigTotalItems = 6;
   // $scope.bigCurrentPage = 1;

    $scope.suppliers = undefined;

    $scope.loadSuppliers = function () {
        Supplier.GET(function (data) {
            $scope.suppliers = data;
          //  $scope.bigTotalItems = data.totalRecords;
          //  $scope.numPages = $scope.bigTotalItems/$scope.pageSize;
        });
    };

    $scope.loadSuppliers();

    $scope.showsupplierInDetail = function (supplier) {
        $location.path('suppliers/' + supplier.id);
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