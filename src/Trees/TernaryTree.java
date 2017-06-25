package Trees;

class TernaryTree{
	public static void main(String args[]){
		Functionality f=new Functionality();
		f.takeInput();
	}
}
 
class Functionality{
	void takeInput(){
		TernaryTreeT t=new TernaryTreeT();
		t.insert("B");
		t.insert("BALA");
		t.insert("BINGO");
		t.insert("BINGO");
 
		char[] c=new char[1000];
		t.traversal(t.root,c,0);
 
		if(t.search(t.root,"BALA",0)){
			System.out.println("BALA is present");
		}
		if(t.search(t.root,"BINGO",0)){
			System.out.println("BINGO is present");
		}
		if(t.search(t.root,"SHER",0)){
			System.out.println("SHER is present");
		}
		if(t.search(t.root,"B",0)){
			System.out.println("B is present");
		}
		if(t.search(t.root,"BI",0)){
			System.out.println("BI is present");
		}
	}
}
 
class TernaryTreeT{
	static TTNode root;
	void insert(String s){
		root=insertNode(root,s,0);
	}
	TTNode insertNode(TTNode node,String s,int index){
		if(node==null){
			//System.out.println("Inserted char "+s.charAt(index));
			TTNode temp=new TTNode(s.charAt(index));
			if(s.length()-1==index){
				temp.end=true;
				node=temp;
				return node;
			}			
			else{
				 temp.equal=insertNode(temp.equal,s,index+1);
				 node=temp;
				 return node;
			}
		}
		//System.out.println("Char at curr node is"+node.data);
		if(s.charAt(index)<node.data){
			node.left=insertNode(node.left,s,index);
		}
		else if(s.charAt(index)>node.data){
			node.right=insertNode(node.right,s,index);
		}
		else{
			if(index==s.length()-1){
				node.end=true;
				return node;
			}
			else{
				node.equal=insertNode(node.equal,s,index+1);
			}
		}
		return node;
	}
 
	Boolean search(TTNode node,String s,int index){
		if(node==null){
			return false;
		}
 
		if(s.charAt(index)<node.data){
			return search(node.left,s,index);
		}
		else if(s.charAt(index)>node.data){
			return search(node.right,s,index);
		}
		else{
			if(index==s.length()-1){
				if(node.end){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return search(node.equal,s,index+1);
			}
		}
	}
 
	void traversal(TTNode node,char[] c,int index){
		if(node==null){
			return;
		}
 
		traversal(node.left,c,index);
		//System.out.println("Putting data "+node.data+" at index "+index);
		c[index]=node.data;
		if(node.end){
			String s=new String(c,0,index+1);
			//String sub=s.substring(0,index);
			System.out.println("String is "+s);
		}
		traversal(node.equal,c,index+1);
		traversal(node.right,c,index);
	}
}
 
class TTNode{
	char data;
	TTNode right;
	TTNode left;
	TTNode equal;
	Boolean end;
	TTNode(char data){
		this.right=null;
		this.left=null;
		this.equal=null;
		this.data=data;
		this.end=false;
	}
}