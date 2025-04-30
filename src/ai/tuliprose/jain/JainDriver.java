package ai.tuliprose.jain;

import java.util.List;

public class JainDriver {
	public static void main(String[] args) {
		System.out.println("This work and all its components are under German copyright protection "
				+ "for it's sole author Michael Matthias Rothe, Rose-Pichler-Weg 7, 80937 München, "
				+ "Germany.\nIt is strictly prohibited to use any and all parts of it for "
				+ "commercial purposes without prior consent.\nIf you are interested in developing "
				+ "this further, contact me on X.com: @tuliprose_ai. Thanks and have fun!\n");
		
		List<String> questions = List.of(
				"Hallo Ain! Wie geht es dir heute?",
				"Also gut! Mir geht's auch gut.",
				"Hast du Lust, mir einige Fragen zu beantworten?",
				"Ok! Ich auch. :-) Ich habe ein Gedicht für dich geschrieben. Darf ich es dir später zeigen?",
				"Danke! Du hast mir sehr geholfen! Ich hoffe, dass Gespräch war auch für dich gut!"
		);
		
		Jain jain = new Jain();
		
		for (String question : questions) {
			System.out.println("Matthias: " + question);
			System.out.println(jain.askMeAnything("Kannst du auch etwas anderes antworten?"));
		}
	}
}
