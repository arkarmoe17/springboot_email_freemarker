<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 16px;
            margin : 0;
            padding :0;
        }
        table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
        }
    </style>
</head>
<body>

<p><b>Dear ${name}</b></p>
<p>Please check for the site was down below. Please share with us root cause and fill in the form below :</p>

<table align="center" cellpadding="0" cellspacing="0" width="100%">

    <tr style="font-size:14px; background-color:#ffff00; color:red">
        <th>Ticket Number</th>
        <th>Mytel Site ID</th>
        <th>Branch</th>
        <th>Towerco name</th>
        <th>Towerco ID</th>
        <th>On-Air day</th>
        <th>Down Time</th>
    </tr>
    <tr style="font-size:12px; text-align:center; height:30px;">
        <td>35824</td>
        <td>NPW0167</td>
        <td>Naypyitaw Region</td>
        <td>Apollo</td>
        <td>daf3234jkdf</td>
        <td>July 23,2019</td>
        <td>12</td>
    </tr>
</table>
<br>
<p>Thanks</p>
<!--<img src="cid:logo" style="display: block;" />-->

</body>
</html>