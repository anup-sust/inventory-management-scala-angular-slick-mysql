/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('CategoriesCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {


   // $scope.pageSize = 2;
   // $scope.maxSize = 5;
   // $scope.numPages = 10;
   ////   $scope.bigTotalItems = 6;
   // $scope.bigCurrentPage = 1;

    $scope.categories = undefined;

    $scope.loadCategories = function () {
        Category.GET(function (data) {
            $scope.categories = data;
          //  $scope.bigTotalItems = data.totalRecords;
          //  $scope.numPages = $scope.bigTotalItems/$scope.pageSize;
        });
    };

    $scope.loadCategories();

    $scope.showcategoryInDetail = function (supplier) {
        $location.path('categories/' + supplier.id);
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