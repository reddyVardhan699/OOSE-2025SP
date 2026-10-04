import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Usertest {

    // Simple music-analysis interface used for testing
    interface MusicAnalyzer {
        String analyze(String song);
    }

    // Small class representing part of our music application
    static class MusicAnalysis {
        private final MusicAnalyzer analyzer;

        MusicAnalysis(MusicAnalyzer analyzer) {
            this.analyzer = analyzer;
        }

        String analyzeSong(String song) {
            return analyzer.analyze(song);
        }
    }

    // Test 1: Normal unit test
    @Test
    void testMusicAnalysis() {

        MusicAnalyzer analyzer = song -> "Completed";

        MusicAnalysis analysis = new MusicAnalysis(analyzer);

        String result = analysis.analyzeSong("MySong.mp3");

        assertEquals("Completed", result);
    }

    // Test 2: Mock
    @Test
    void testMusicAnalysisMock() {

        class MockMusicAnalyzer implements MusicAnalyzer {

            boolean called = false;

            @Override
            public String analyze(String song) {
                called = true;
                return "Mock Result";
            }
        }

        MockMusicAnalyzer mock = new MockMusicAnalyzer();

        MusicAnalysis analysis = new MusicAnalysis(mock);

        String result = analysis.analyzeSong("Song.mp3");

        assertEquals("Mock Result", result);
        assertTrue(mock.called);
    }

    // Test 3: Stub
    @Test
    void testMusicAnalysisStub() {

        MusicAnalyzer stub = song -> "Stub Result";

        MusicAnalysis analysis = new MusicAnalysis(stub);

        String result = analysis.analyzeSong("TestSong.mp3");

        assertEquals("Stub Result", result);
    }
}