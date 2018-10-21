'use strict'
var app = angular.module("mainApp");

app.service("ModuleCrudService", function($http){
  this.saveModule = function saveModule(data){
    return $http({
      method : 'POST',
      url : '/createModule/',
      data : {
        name: data.name,
        data: data.data
      }
    })
  }
})
