/**
 * Created by Anup_sust on 8/17/2015.
 */
invMgmt.factory('Supplier', ['$resource', function ($resource) {

    var Supplier = $resource('/api/supplier/:id/:detail/:did', {}, {
        GET: {method: 'GET', params: {id: '@id'}},
        DELETE: {method: 'DELETE', params: {id: '@id'}},
        GET_DETAILS: {method: 'GET', params: {detail: 'details', id: '@id'}},
        GET: {method: 'GET'},
        POST: {method: 'POST'},
        PUT: {method: 'PUT'}
    });

    Supplier.prototype.update = function (success, error) {
        Supplier.PUT(this, success, error);
    };

    Supplier.add = function (obj, success, error) {
        Supplier.POST(obj, success, error);
    };

    return Supplier;

}]);