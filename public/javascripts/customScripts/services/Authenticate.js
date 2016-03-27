/**
 * Created by Anup_sust on 8/17/2015.
 */
invMgmt.factory('Authenticate', ['$resource', function ($resource) {

    var Authenticate = $resource('/api/authenticate', {}, {
        POST: {method: 'POST'},
        PUT: {method: 'PUT'}
    });

    return Authenticate;

}]);