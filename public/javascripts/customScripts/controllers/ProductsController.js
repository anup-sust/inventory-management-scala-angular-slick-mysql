/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductsCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {


    $scope.pageSize = 10;
    $scope.maxSize = 5;
    $scope.numPages = 10;
   //   $scope.bigTotalItems = 6;
  // $scope.bigCurrentPage = 2;

    $scope.productsDetails = undefined;

    $scope.loadProducts = function () {
        Product.GET_DETAILS({
            pageSize: $scope.pageSize,
            page: $scope.big.CurrentPage - 1,
            search: $scope.search
        }, function (data) {
            $scope.productsDetails = data;
            $scope.bigTotalItems = data.totalRecords;
          //  $scope.numPages = $scope.bigTotalItems/$scope.pageSize;
        });
    };

    $scope.loadProducts();

    $scope.showProductInDetail = function (product) {
        $location.path('products/' + product.id);
        //console.log(product);
    };


    //$scope.totalItems = 64;
    //$scope.currentPage = 4;

    //$scope.setPage = function (pageNo) {
    //    $scope.bigCurrentPage = pageNo;
    //    $scope.loadProducts();
    //};

    $scope.pageChanged = function () {
        $scope.loadProducts();
    };


}]);