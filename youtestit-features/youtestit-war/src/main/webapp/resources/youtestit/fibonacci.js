/*******************************************************************************
*
*   YouTestit source code:
*   ======================
*   Licensed to the Apache Software Foundation (ASF) under one or more
*   contributor license agreements.  See the NOTICE file distributed with
*   this work for additional information regarding copyright ownership.
*   The ASF licenses this file to You under the Apache License, Version 2.0
*   (the "License"); you may not use this file except in compliance with
*   the License.  You may obtain a copy of the License at
*  
*       http://www.apache.org/licenses/LICENSE-2.0
* 
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*
*   Links:
*   ======
*   Homepage : http://www.youtestit.org
*   Git      : https://github.com/youtestit
*
*******************************************************************************/

var DEFAULT_STYLE_CLASS = "fibonacciItem";
var ENABLE_STYLE_CLASS  = "fibonacciItemEnable";
var OVER_STYLE_CLASS    = "fibonacciItemOver";

function fibonacci(field,value,index){
	// attributs
	var htmlFieldParent = document.getElementById(field+"_field");
	var htmlFieldIndex = document.getElementById(field+"_field_index");

	// set hidden input value
	var htmlField   = htmlFieldParent.childNodes[0];
	htmlField.value =  value;
	htmlFieldIndex.value=index;
	
	// change class
	fibonacciClass(field,index,ENABLE_STYLE_CLASS);
}


function fibonacciOver(field,index){
	fibonacciForceClean(field)
	fibonacciClass(field,index,OVER_STYLE_CLASS);
}

function fibonacciClass(field,index, styleClass){
	var htmlList = document.getElementById(field+"_list");
	var count = index;
	for(var i=0; i<htmlList.childNodes.length; i++){
		var child = htmlList.childNodes[i];
		
		if(child!=null && child.nodeName=="LI"){
			child.setAttribute("class",styleClass);
			count --;
		}
		
		if(count==0||count<0){
			break;
		}
	}
}

function fibonacciForceClean(field){
	var htmlList = document.getElementById(field+"_list");
	for(var i=0; i<htmlList.childNodes.length; i++){
		var child = htmlList.childNodes[i];
		
		if(child!=null && child.nodeName=="LI"){
			child.setAttribute("class",DEFAULT_STYLE_CLASS);
		}
	}
}

function fibonacciCleanClass(field,index){
	var htmlList = document.getElementById(field+"_list");
	var htmlFieldIndex = document.getElementById(field+"_field_index");
	var count = 0 ;
	if(htmlFieldIndex.value==""){
		count --;
	}else{
		count +=htmlFieldIndex.value;
	}
	
	for(var i=0; i<htmlList.childNodes.length; i++){
		var child = htmlList.childNodes[i];
		
		if(child!=null && child.nodeName=="LI"){
			if(count>= 0){
				child.setAttribute("class",ENABLE_STYLE_CLASS);	
			}else{
				child.setAttribute("class",DEFAULT_STYLE_CLASS);
			}
			count --;
		}
	}
}
