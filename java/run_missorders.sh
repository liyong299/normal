#!/bin/sh

##java -jar missorders.1.0.jar "jdbc:mysql://192.168.100.59:3306/cec?autoReconnect=true&amp;characterEncoding=UTF-8" "cec" "aU!0MdVg" "CEC_OrderSendRecord" "CEC_TicketOrder" "-10"

srcUrl="jdbc:mysql://172.16.34.48:3306/cec?autoReconnect=true&amp;characterEncoding=UTF-8&allowMultiQueries=true";
srcUsername="cec";
srcPassword="cec";

destUrl="jdbc:mysql://172.16.34.13:3306/cec?autoReconnect=true&amp;characterEncoding=UTF-8&allowMultiQueries=true";
destUsername="cec";
destPassword="cec";

java_cp="/home/cec/monitor/monitor/mysql-connector-java-5.1.31.jar"

java -cp ${java_cp} -Djava.ext.dirs=/home/cec/monitor/monitor/ -jar bcsync.jar ${srcUrl} ${srcUsername} ${srcPassword} ${destUrl} ${destUsername} ${destPassword}
