interface ConnectionPool{
	
	boolean initialize(int size);//5,  5 conn objects
	Connection getConnection();// return any one conn obj
	void returnConnection(Connection conn);//if conn

}

class Connection{
	
	public Connection(){

	}
	boolean equals(){
	//TODO
	}
	int hashCode(){
	//TODO
	}
}

class TakeAndReturnConnection implements ConnectionPool{

	int poolSize=0,poolInitSize=0;
	List<Connection> connectionList=new ArrayList<>();
	HasMap<Connection,boolean> takenFromPoolMap=new HasMap<Connection,boolean>();
	ConnectionObject conn;
	@Override
	Connection getConnection(){
		if(poolSize>0){
			
		conn=connectionList.get(0);
		takenFromPoolMap.put(conn,true);
		connectionList.remove(0);
		this.poolSize--;
		return conn;
			
		}
		else{
			//Can provide logging as well
			return null;
		}
	}

	@Override
	boolean initialize(int size){

		poolInitSize=size;
		for(int i=0;i<size;i++){
			this.connectionList.add(new Connection());
		}
		this.poolSize=connectionList.size();
		return true;
	}

	@Override
	void returnConnection(Connection conn){
		if(this.poolSize< poolInitSize){
			if(takenFromPoolMap.containsKey(conn)){
				connectionList.add(conn);
				//can provide success log
				this.poolSize++;
			}
		}else{
			//can provide failure log
		}

	}



}
