
function LoginSvc($http,$state,$sessionStorage, carConstants){

  function login(username, password) {


     // $cookieStore.put('globals', $rootScope.globals);
      var req = {
          method: 'POST',
          url: carConstants.loginUrl,
          data: { username: username, password: password }
      }



  }

  function logout() {
    $sessionStorage.user = null;
  }

  return {
    login: login,
    logout: logout
  }

}

const serviceConfig = [
  '$http',
  '$state',
  '$sessionStorage',
  'carConstants',
  LoginSvc
]

angular.module('car')
  .factory('LoginSvc', serviceConfig)

module.exports = {
  name: 'LoginSvc',
  factory: serviceConfig
}
