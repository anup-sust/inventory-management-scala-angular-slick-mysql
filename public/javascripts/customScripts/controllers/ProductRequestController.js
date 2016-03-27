invMgmt.controller('ProductRequestsCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {

    $scope.pageSize = 10;
    $scope.maxSize = 5;
    $scope.numPages = 10;

    $scope.big = {CurrentPage:1 };

    $scope.loadProductRequests = function () {
        Product.GET_REQUEST_DETAIL({
            pageSize: $scope.pageSize,
            page: $scope.big.CurrentPage - 1,
            search: $scope.search
        }, function (data) {
            $scope.productRequests = data;
            $scope.bigTotalItems = data.totalRecords;
        });
    };

    $scope.loadProductRequests();

    $scope.pageChanged = function () {
        $scope.loadProductRequests();
    };

    $scope.requestsStatus = [{id: "Pending", value: "Pending"},
        {id: "Approved", value: "Approved"},
        {id: "Denied", value: "Denied"},
        {id: "Delivered", value:"Delivered"}
    ];

    $scope.updateProductRequest = function (productRequest) {
        Product.UPDATE_REQUEST(productRequest, function (data) {
            productRequest.token = data.token;
        });
    };

    $scope.delete = function (prod) {
        Product.DELETE_REQUEST({id:prod.id}, function () {
            $scope.loadProductRequests();
        });
    };

}]);