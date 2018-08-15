'use strict';

function BookSvc($q, Restangular){

    let resource = Restangular.one('api/bookings');


    function fnAdd(data) {
        let def = $q.defer();

        let promise = resource.customPOST(data);

        promise.then(
            function (objResponse) {
                def.resolve(angular.copy(objResponse.data));
            }, function (objResponse) {
                def.resolve(angular.copy(objResponse.data));
            }
        );

        return def.promise;
    }

    return {
        fnAdd: fnAdd
    }

}



const serviceConfig = [
    '$q',
    'Restangular',
    BookSvc
]

angular.module('car')
    .factory('BookSvc', serviceConfig)

module.exports = {
    name: 'BookSvc',
    factory: serviceConfig
}
