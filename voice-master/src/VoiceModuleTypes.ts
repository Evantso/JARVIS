import { EventSubscriptionVendor } from 'react-native';

type Callback = (error: string) => void;

export type VoiceModule = {
  /**
   * Gets list of SpeechRecognitionServices used.
   * @platform android
   */
  getSpeechRecognitionServices: () => Promise<string[]> | void;
  destroySpeech: (callback: Callback) => void;
  destroyTranscription: (callback: Callback) => void;
  startSpeech: Function;
  startTranscription: Function;
  stopSpeech: (callback: Callback) => void;
  stopTranscription: (callback: Callback) => void;
  cancelSpeech: (callback: Callback) => void;
  cancelTranscription: (callback: Callback) => void;
  isRecognizing: Function;
  isSpeechAvailable: Function;
} & SpeechEvents &
  TranscriptionEvents &
  EventSubscriptionVendor;

export type SpeechEvents = {
  onSpeechStart?: (e: SpeechStartEvent) => void;
  onSpeechRecognized?: (e: SpeechRecognizedEvent) => void;
  onSpeechEnd?: (e: SpeechEndEvent) => void;
  onSpeechError?: (e: SpeechErrorEvent) => void;
  onSpeechResults?: (e: SpeechResultsEvent) => void;
  onSpeechPartialResults?: (e: SpeechResultsEvent) => void;
  onSpeechVolumeChanged?: (e: SpeechVolumeChangeEvent) => void;
};

export type TranscriptionEvents = {
  onTranscriptionStart?: (e: TranscriptionStartEvent) => void;
  onTranscriptionEnd?: (e: TranscriptionEndEvent) => void;
  onTranscriptionError?: (e: TranscriptionErrorEvent) => void;
  onTranscriptionResults?: (e: TranscriptionResultsEvent) => void;
};

export type SpeechStartEvent = {
  error?: boolean;
};

export type TranscriptionStartEvent = {
  error?: boolean;
};

export type SpeechRecognizedEvent = {
  isFinal?: boolean;
};

export type SpeechResultsEvent = {
  value?: string[];
};

export type TranscriptionResultsEvent = {
  segments?: string[];
  transcription?: string;
  isFinal?: boolean;
};

export type SpeechErrorEvent = {
  error?: {
    code?: string;
    message?: string;
  };
};

export type TranscriptionErrorEvent = {
  error?: {
    code?: string;
    message?: string;
  };
};

export type SpeechEndEvent = {
  error?: boolean;
};

export type TranscriptionEndEvent = {
  error?: boolean;
};

export type SpeechVolumeChangeEvent = {
  value?: number;
};
