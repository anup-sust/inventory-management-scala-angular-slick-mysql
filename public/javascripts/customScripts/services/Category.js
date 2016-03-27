/**
 * Created by Anup_sust on 8/17/2015.
 */
invMgmt.factory('Category', ['$resource', function ($resource) {

    var Category = $resource('/api/category/:id/:detail/:did', {}, {
        GET: {method: 'GET', params: {id: '@id'}},
        DELETE: {method: 'DELETE', params: {id: '@id'}},
        GET_DETAILS: {method: 'GET', params: {detail: 'details', id: '@id'}},
        GET: {method: 'GET'},
        POST: {method: 'POST'},
        PUT: {method: 'PUT'}
    });
    Category.prototype.update = function (success, error) {
        Category.PUT(this, success, error);
    };

    Category.add = function (obj, success, error) {
        Category.POST(obj, success, error);
    };

    return Category;

}]);