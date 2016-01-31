//AngularJS Controller.

app.controller('RootCtrl', ['$rootScope', '$scope', '$state', 'Page',
  function ($rootScope, $scope, $state, Page) {

	$scope.$Page = Page;

  $rootScope.APIRootUrl = "http://localhost:8080/angular-jaxrs-demo/";
	
    $scope.home = function() {
      $state.go('home').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          //Page.setTitle('Home');
      });
    };

    $scope.about = function() {
      $state.go('about').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          //Page.setTitle('About');
      });
    };
    
    $scope.converters = function() {
        $state.go('converters').then(function() {
      	  $('body').toggleClass('pushed-right');
            $('#navbar').toggleClass('in');
            //Page.setTitle('Converters');
        });
      };
    
    $scope.contact = function() {
        $state.go('contact').then(function() {
      	  $('body').toggleClass('pushed-right');
            $('#navbar').toggleClass('in');
            //Page.setTitle('Contact');
        });
    };
      
    $scope.search = function() {
      $state.go('search').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          //Page.setTitle('Search');
      });
    };

    $scope.login = function() {
      $state.go('login').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          //Page.setTitle('Log In');
      });      
    };
  }
]);

app.controller('LogInController', ['$rootScope', '$scope', '$http', 'DataService',
  function ($rootScope, $scope, $http, DataService) {
	
  	$scope.validated = false;
  	
  	$scope.validateUser = function(){	
  		
  		$http.get($rootScope.APIRootUrl + "webapi/validateuser/"+$scope.username+"/"+$scope.password)
  	    .then(function(response) {
  	    	$scope.result = JSON.stringify(response.data);
  	    	$scope.validated = response.data;
  	    	console.log(response.data);
  	    	//alert("test");
      	});
  	}	
  }
]);

app.controller('getWeatherController', ['$rootScope', '$scope', '$http', 'DataService',
    function ($rootScope, $scope, $http, DataService) {
	
  		$scope.checked = false;
  		
  		$scope.calculate = function(){	
  			var num = $scope.inputNum;
  			if(num == undefined || num == ""){
  				num = "";
  			}
  			//num = "";
  			$http.get($rootScope.APIRootUrl + "webapi/ftocservice/"+num)
  		    .then(function(response) {
  		    	$scope.result = JSON.stringify(response.data);
  		    	$scope.checked = true;
  		    	console.log(response);
  		    	//alert("test");
  	    	});
  		}
    }
]);