/**
 * Created by Anup_sust on 8/17/2015.
 */

invMgmt.controller('SignInCtrl',['$scope', '$resource','$location', '$rootScope', function ($scope, $resource, $location, $rootScope) {



    if($scope.account.name) $location.path('/');


    $scope.signIn = {};


    var Authenticate = $resource('/api/login', {}, {
        POST: {method: 'POST'}
    });

    $scope.login = function (user,pass) {
        if($scope.loginform.$invalid) return;
        Authenticate.POST({userId: user, password: pass}, function (response) {
            $rootScope.account = response;
            $rootScope.setRole();
            if($rootScope.account.name) $location.path('/');
            else $scope.signIn.invalidSignIn = true;
        });
    };
}]);