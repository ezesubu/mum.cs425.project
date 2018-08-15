'use strict';
var angular = require('angular');

require('../css/booking.css')

function bookingCtrl($scope, $sessionStorage, $state, $filter,$uibModal, LoginSvc, BookSvc) {
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



  $scope.signout = signout;
  $scope.fnBook= fnBook;


  function signout(){
    LoginSvc.logout()
    $state.go('login')
  }

  function fnBook(){

      let promise = BookSvc.fnAdd($state.params.car);
      promise.then(function (objData) {
         // $state.go('booking', {car: id});
          console.log("Payment");
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
  '$uibModal',
  'LoginSvc',
  'BookSvc',
]

function routeConfig($stateProvider) {
  $stateProvider.state(stateConfig)
}

angular.module('car')
  .controller('bookingCtrl', bookingCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
