import java.util.ArrayList;

public class Skywalker {
	static String name;
	public final NeuralNetwork nn;
	ArrayList scoreList = new ArrayList();//////////////////////////////////////////NEW
	
	Skywalker(String name){
		this.name = name;
		nn = new NeuralNetwork(neurons_amount, genomes_per_generation, 0.5, -1, 1, this.name);
	}
	
	@Override
	public String toString() {
		 return this.name;
	}
	
	private final int genomes_per_generation = 3;
    private final int neurons_amount[] = {2, 2, 1};
	private final double inputs[] = new double[2];
    private double outputs[] = new double[1];
    public int score = 0;
	
	static Ball ball;
	static PongGUI pong;
	
	protected int learn(double y) {        
        // Get the inputs from the game and the output from the neural network
        inputs[0] = ball.ball.getCenterY();
//        inputs[1] = pong.rightBrick.brick.getY();
        inputs[1] = y;
        outputs = nn.getOutputs(inputs);

        // Do an action according to the output value
        if(outputs[0] > 0.5) {
            return -10;
        }
        else {
        	return 10;
        }
    }
	
	protected void gameOver() throws InterruptedException {
        // Get the fitness of the current genome, then create a new genome
        nn.newGenome(score);
        
        // RESET ALL
        
        ball.starrtValues();
        
        scoreList.add(score);//////////////////////////////////////////NEW
        
       score = 0;

    }

}
