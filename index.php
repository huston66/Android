<?php 
   header("Access-Control-Allow-Origin:* ");
   $sa=$_POST["sa"];
   $city=$_POST["ct"];
   $state=$_POST["st"];
   $degree=$_POST["degree"];
   $sa=urlencode($sa);
   $city=urlencode($city);
   $state=urlencode($state);
   $googlekey="AIzaSyDQuAJ5a3QxzK7Q2CkVxMcIe6kK-peamZQ";
   $url="https://maps.googleapis.com/maps/api/geocode/xml?address=$sa,$city,$state&key=$googlekey";
   
   $xml=simplexml_load_file($url);
   if($xml->status!="ZERO_RESULTS")
   {
      $latitude=$xml->result->geometry->location->lat;
      $longitude=$xml->result->geometry->location->lng;

      $apikey="1e4af11a990a23119b08a625461de0df";
      if($degree == "Fahrenheit")
         $uv="us";
      else
         $uv="si";
      $sea="https://api.forecast.io/forecast/$apikey/$latitude,$longitude?units=$uv&exclude=flags";
      
      $json_string = file_get_contents($sea);
      echo $json_string;
   }
?> 
