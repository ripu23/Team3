(function() {
  'use strict'
  var app = angular.module("mainApp");
  app.controller("CreateModuleController", [
    '$scope',
    'CreateId',
    'ModuleCrudService',
    function($scope, CreateId, ModuleCrudService) {
      console.log('%c Reached Module Controller', 'color:green');
      $(function() {
        $('[data-toggle="tooltip"]').tooltip()
      })
      $scope.moduleObject = {};
      $scope.moduleObject.attributes = [];
      const dummy = {
        id: "",
        label: "Label"
      }

      let newObj = Object.create(dummy);
      newObj.id = CreateId.create();
      newObj.label = "Label"
      $scope.moduleObject.attributes.push(newObj);

      $scope.moduleType = {
        "name": "Module Type",
        "value": "Object",
        "values": ["Object", "Event"]
      }

      $scope.addAttributes = function() {
        newObj = Object.create(dummy);
        newObj.id = CreateId.create();
        newObj.label = "Label";
        $scope.moduleObject.push(newObj);
      }

      $scope.removeAttributes = function() {
        if ($scope.moduleObject.length > 1) {
          $scope.moduleObject.pop();
        }
      }

      $scope.saveModule = function() {
        ModuleCrudService.saveModule({
          data : $scope.moduleObject
        }).then(function success(response){
          alertify.success('Successfuly created');
        }, function error(err){
          alertify.error('Something is wrong with the API');
        })
      }

    }
  ]);
})();
