/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductDetailCtrl', ['$scope', '$resource', 'Product', '$stateParams', '$location', 'Supplier', 'Category', 'Productstatus', '$modal', function ($scope, $resource, Product, $stateParams, $location, Supplier, Category, Productstatus, $modal) {

    $scope.selectedProduct = undefined;
    Product.GET({id: $stateParams.id}, function (data) {
        $scope.selectedProduct = data;
        Product.GET_REQUEST_DETAIL_FOR_PRODUCT({pid: $scope.selectedProduct.id, status: "Pending"}, function (data) {
            $scope.prRequest = data;
        });
    }, function () {
        $location.path('/products');
    });

    $scope.save = function () {
        $scope.selectedProduct.update(function () {
            $location.path('/products');
        }, function () {

        });
    };

    $scope.delete = function (prod) {
        Product.DELETE({id:prod.id}, function () {
            $location.path('/products');
        });
    };

    $scope.request = function () {

        var proRqstModal = $modal.open({
            templateUrl: "/assets/partials/product-request-modal.html",
            controller: ['$scope', '$modalInstance', '$rootScope', 'selectedProduct', 'Product', function ($scope, $modalInstance, $rootScope, selectedProduct, Product) {

                $scope.productRqst = {amount: 0};

                $scope.selectedProduct = selectedProduct;

                $scope.close = function () {
                    $modalInstance.close(false);
                };

                $scope.sendRequest = function () {
                    Product.ADD_REQUEST({
                        id: $scope.selectedProduct.id,
                        quantity: parseInt($scope.productRqst.amount)
                    }, function () {
                        $modalInstance.close(false);
                    });
                };
            }],
            //backdrop: 'static',
            //keyboard: true,
            //backdropClick: false,
            //windowClass: "modal fade in",
            resolve: {
                selectedProduct: function () {
                    return $scope.selectedProduct
                }
            }
        });

        proRqstModal.result.then(function (result) {

        });


        //  Product.ADD_REQUEST({id: $scope.selectedProduct.id, quantity: 5});
    };


}]);