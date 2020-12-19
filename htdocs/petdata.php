<?php
$id=$_GET["id"];
$pos = strpos($id, "WCT");
if ($pos === false) {
    $ch = curl_init('https://animal-id.net/ua/animal/profile/'.$_GET["id"]);
} else {
	$ch = curl_init('https://animal-id.net/ua/animal/search?search_transponder='.$_GET["id"]);
}
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
curl_setopt($ch, CURLOPT_AUTOREFERER, TRUE);
$result = curl_exec($ch);
$doc = new DOMDocument();
$doc->loadHTML(mb_convert_encoding($result, 'HTML-ENTITIES', 'UTF-8'));

$finder = new DomXPath($doc);
$name =$finder->query("//*[contains(@class, 'user-details-desc__name col-lg-6 col-md-6 col-sm-6')]")->item(0)->nodeValue;
$number =ltrim($finder->query("//*[contains(@class, 'main-button_call__item main-button_call__item_phone')]")->item(0)->getAttribute("href"),"tel:");
$img =$finder->query("//*[contains(@class, 'img_response_post')]")->item(0)->getAttribute("src");
$data =$finder->query("//*[contains(@class, 'user-details-desc-column-parameters-item__detail')]");

$arr->name = ltrim($name,"\n");
$arr->view = ltrim($data->item(0)->nodeValue,"\n");
$arr->dateOfBirth = ltrim($data->item(1)->nodeValue,"\n");
$arr->breed = ltrim($data->item(2)->nodeValue,"\n");
$arr->color = ltrim($data->item(3)->nodeValue,"\n");
$arr->sex = ltrim($data->item(4)->nodeValue,"\n");
$arr->registrationDate = ltrim($data->item(5)->nodeValue,"\n");
$arr->owner = ltrim($data->item(6)->nodeValue,"\n");
$arr->number = ltrim($number,"\n");
$arr->sterilization = ltrim($data->item(8)->nodeValue,"\n");
$arr->animalId = ltrim($data->item(9)->nodeValue,"\n");
$arr->microchip = ltrim($data->item(10)->nodeValue,"\n");
$arr->QRpassport = ltrim($data->item(11)->nodeValue,"\n");
$arr->otherIdentifiers = ltrim($data->item(12)->nodeValue,"\n");
$arr->img = ltrim($img,"\n");

$myJSON = json_encode($arr, JSON_UNESCAPED_UNICODE);

echo $myJSON;
?>