package ai.tuliprose.jain;

import java.util.ArrayList;
import java.util.List;

import ai.tuliprose.libs.quintean.Quintean;
import ai.tuliprose.libs.quintean.QuinteanNode;
import ai.tuliprose.libs.quintean.QuinteanRepository;

public class Jain {
	private final QuinteanRepository repository;
	private final QuinteanNode output;
	
	public Jain() {
		List<Quintean> model = transformInputToQuinteans("^�W� =�");
		repository = QuinteanRepository.builder().withQuinteans(model).build();
		output = new QuinteanNode(Quintean.MEDIUM);
		
		for (int i = 0; i < 5; i++) {
			repository.expand();
			repository.expand();
			repository.shrink();
		}
		
		attachOutputToModel(output, repository.getLeafNodes());		
	}
	
	private void attachOutputToModel(QuinteanNode output, List<QuinteanNode> model) {
		for (QuinteanNode node : model) {
			node.addChild(output);
		}
	}
	
	public String askMeAnything(String question) {
		List<Quintean> input = transformInputToQuinteans(question);
		attachInputToModel(input);
		
		repository.activate();
		double outputValue = output.calculateOutput();
		
		return (outputValue + "").substring(0, 5) + "% Ja!";
	}

	private void attachInputToModel(List<Quintean> input) {
		for (QuinteanNode node : repository.getRootNodes()) {
			for (Quintean quintean : input) {
				node.addParent(new QuinteanNode(quintean));
			}
		}
	}

	private List<Quintean> transformInputToQuinteans(String input) {
		List<Quintean> quinteans = new ArrayList<>();
    	
    	byte[] inputBytes = input.getBytes();
    	byte[] compatible;
    	
    	if (inputBytes.length % 3 != 0) {
    		compatible = new byte[inputBytes.length + invert(inputBytes.length)];
    	} else {
    		compatible = inputBytes;
    	}
    	
		boolean[] bitset = new boolean[3];

		for (int i = 0; i < compatible.length; i++) {
			for (int bit = 0; bit < 8; bit++) {
				bitset[bit % 3] = ((compatible[i] >> (7 - bit)) & 1) == 1;
				if ((bit + 1) % 3 == 0) {
					quinteans.add(Quintean.valueOf(bitset));
				}
			}
		}
		
		return quinteans;
	}

	private int invert(int i) {
		return i == 1 ? 2 : i == 2 ? 1 : invert(i % 3);
	}
}
