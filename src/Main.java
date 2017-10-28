
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

//based on Head First Java book
public class Main {

	public void addNote(int note, Track track) {
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, note, 100);
			MidiEvent noteOn = new MidiEvent(a, 1);
			track.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, note, 100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			track.add(noteOff);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addNote method problem");
		}
	}

	public void play() {
		try {
			Sequencer sequence = MidiSystem.getSequencer();
			System.out.println("Successfully got a sequencer");

			sequence.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);

			Track track = seq.createTrack();
			addNote(44, track);
			addNote(56, track);
			addNote(90, track);
			
			sequence.setSequence(seq);

			sequence.start();

		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			e.printStackTrace();
			System.out.println("We got a problem!");
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.play();
	}

}
