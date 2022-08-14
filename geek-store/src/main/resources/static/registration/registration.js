angular.module('front').controller('registrationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';


    $scope.registrationUser = function () {
        $http.post(contextPath + '/registration', $scope.user)
            .then(function successCallback(response) {

            }, function errorCallback(response) {
            });
    };




});
