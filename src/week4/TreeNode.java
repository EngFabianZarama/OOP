//Fabian Zarama
//109599744
//CSE 214
//Homework #4

package week4;

public class TreeNode {
	private TreeNode[] branch;// 0 = left, 1 = mid, 2 = rigth
	private String label; // It will be used when constructing the tree
	private String message; // The message will be displayed to the screen. It
							// will either be a question or just a normal
							// message.
	private String prompt; // displayed to the screen as a possible answer to a
							// question.

	/**
	 * TreeNode() constructor to initialize each TreeNode to null
	 */
	public TreeNode(int num) {
		branch = new TreeNode[num];
		for (int i = 0; i < branch.length; i++) {
			branch[i] = null;
		}
	}

	/**
	 * 
	 * @return false if the node contains a TreeNode else return true
	 */
	public boolean isLeaf() {
		for (int i = 0; i < branch.length; i++) {
			if (this.branch[i] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a reference to the TreeNode that has the given label. The return
	 * value is null if the label is not found.
	 * 
	 * @param label
	 * @return the reference to the TreeNode else return null
	 */
	public TreeNode getNodeReference(String label) {

		TreeNode found = null;

		for (int i = 0; i < this.getBranch().length; i++) {
			if (this.getLabel().equals(label)) {
				return this;
			} else {

				if (this.getBranch()[i] != null) {
					found = this.getBranch()[i].getNodeReference(label);
					if (found != null) {
						return found;
					} 
				}else {
					continue;
				}
			}
		}
		return found;

	}

	/**
	 * Traverses the tree in preorder, and prints the contents of the tree to
	 * the screen.
	 */
	public void preOrder() {

		System.out.println("Label: " + this.getLabel());
		System.out.println("Prompt: " + this.getPrompt());
		System.out.println("Message: " + this.getMessage());
		System.out.println();
		for (int i = 0; i < this.getBranch().length; i++) {
			if (this.getBranch()[0]==null || this.getBranch()[0].getBranch()[0]==null ) {
				
				int j = 0;
				while (this.getBranch()[j] != null) {
					System.out.println("Label: " + this.getBranch()[j].getLabel());
					System.out.println("Prompt: " + this.getBranch()[j].getPrompt());
					System.out.println("Message: " + this.getBranch()[j].getMessage());
					System.out.println();
					j++;
					if(j==9){
						return;
					}
				}
				return;
			}
			if(this.branch[i]==null){
			continue;
			}else{
				this.branch[i].preOrder();
			}
		}
	}
	
	

	/**
	 * @return the branch
	 */
	public TreeNode[] getBranch() {
		return branch;
	}

	/**
	 * @param branch
	 *            the branch to set
	 */
	public void setBranch(TreeNode[] branch) {
		this.branch = branch;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}

	/**
	 * @param prompt
	 *            the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

}