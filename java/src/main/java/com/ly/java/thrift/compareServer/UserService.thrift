 namespace java cn.slimsmart.thrift.demo.helloworld
 service UserService{ 
  string addUser(1:string username)
  string getUser(1:string username)
  string updateUser(1:string username)
  string deleteUser(1:string username)
 }