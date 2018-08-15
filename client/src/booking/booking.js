'use strict';
var angular = require('angular');

require('../css/booking.css')

function bookingCtrl($scope, $sessionStorage, $state, $filter, $localStorage, $uibModal, CarSvc, BookSvc) {
  $scope.book = {};
  $scope.title = 'Book a car';
  $scope.navBar = require('../includes/navbar.html')
  $scope.links = $state.get()
    .filter(x => x.name.startsWith('home.'))
    .map(x => {
      return {
        title: x.url.slice(1),
        link: $state.href(x.name)
      }
    });

  fnGetCar();

  $scope.signout = signout;
  $scope.fnBook = fnBook;

   function fnBook() {
       if($localStorage.customer_id ){
           $scope.book.car =  parseInt($state.params.car);
           $scope.book.customer = $localStorage.customer_id;
           let promise = BookSvc.fnAdd($scope.book);
           promise.then(function (objData) {
               console.log(objData);
           });
       }else{
           $state.go("login", {car_id: $state.params.car});
       }
   }

  function signout(){
    LoginSvc.logout()
    $state.go('login')
  }

  function fnGetCar(){
      let promise = CarSvc.fnFind($state.params.car);
      promise.then(function (objData) {
          $scope.car = objData;
      });
  }


    function openComponentModal() {

        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'stackedModal.html',
            controller: ['$scope', '$uibModalInstance', '$location',
                function($scope, $uibModalInstance, $location) {
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss();
                    };

                    $scope.ok= function () {
                        $location.path("/myCart");
                    };
                }
            ]
        });
    };

}

var stateConfig = {
  name: 'booking',
  url: '/booking/:car',
  templateUrl: require('./booking.html'),
  controller: 'bookingCtrl'
};

bookingCtrl.$inject = [
  '$scope',
  '$sessionStorage',
  '$state',
  '$filter',
  '$localStorage',
  '$uibModal',
  'CarSvc',
  'BookSvc',
]

function routeConfig($stateProvider) {
  $stateProvider.state(stateConfig)
}

angular.module('car')
  .controller('bookingCtrl', bookingCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
