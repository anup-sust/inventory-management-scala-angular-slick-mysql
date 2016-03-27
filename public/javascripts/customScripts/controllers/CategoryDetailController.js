/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('CategoryDetailCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', '$stateParams', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus, $stateParams) {


    $scope.selectedCategory=undefined;
    Category.GET({id:$stateParams.id },function (data) {
        $scope.selectedCategory = data;
      //  console.log($scope.selectedSupplier);
    });

    $scope.save = function () {

        $scope.selectedCategory.update(function () {
            $location.path('/categories');
        }, function () {

        });
    };

    $scope.delete = function (prod) {
        Category.DELETE({id:prod.id}, function () {
            $location.path('/categories');
        });
    };




}]);