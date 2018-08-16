'use strict';
var angular = require('angular');

require('../css/booking.css')

function bookingCtrl($scope, $sessionStorage, $state, $filter, $localStorage, $uibModal, moment, SweetAlert,CarSvc, BookSvc, PaymentSvc) {
  $scope.book = {};
  $scope.payment = {};
  $scope.title = 'Book a car';
    $scope.user = $localStorage.customer;
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
       if($localStorage.customer ){

           $scope.book.car =  {"id": parseInt($state.params.car)};
           $scope.book.customer = $localStorage.customer;
           let promise = BookSvc.fnAdd($scope.book);
           promise.then(function (objData) {
               $scope.payment.booking = {id: objData.id};
               $scope.payment.paymentDate = moment().format();
               console.log("the payment 123", $scope.payment);
               let promise = PaymentSvc.fnAdd($scope.payment);
               promise.then(function (objData) {
                   SweetAlert.swal("success", "Payment Done", "success");
                   $state.go("home");
               });
           });
       }else{
           $state.go("login", {car_id: $state.params.car});
       }
   }

  function signout(){
    LoginSvc.logout()
    $state.go('login')
  }
    $scope.$watch("book", function(){
        if($scope.book.start && $scope.book.end){
            let start = moment($scope.book.start);
            let end = moment($scope.book.end);
            $scope.payment.rent_duration = end.diff(start, 'days');
            $scope.payment.amount =  $scope.payment.rent_duration * $scope.car.category.price;
        }
    }, true);

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
  'moment',
  'SweetAlert',
  'CarSvc',
  'BookSvc',
  'PaymentSvc'
]

function routeConfig($stateProvider) {
  $stateProvider.state(stateConfig)
}

angular.module('car')
  .controller('bookingCtrl', bookingCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
