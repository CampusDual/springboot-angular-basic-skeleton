import { Injectable } from '@angular/core';
import { Logger } from '../model/logger';
import { environment } from 'src/environments/environment';

const isDebugMode = environment.isDebugMode;
const noop = (): any => undefined;

@Injectable({
  providedIn: 'root'
})
export class LoggerService implements Logger {

  constructor() {
    console.log('Console Logger Service registered');
  }

  get log() {
    if (isDebugMode) {
      return console.log.bind(console);
    } else {
      return noop;
    }
  }

  get info() {
    if (isDebugMode) {
      return console.info.bind(console);
    } else {
      return noop;
    }
  }

  get warn() {
    if (isDebugMode) {
      return console.warn.bind(console);
    } else {
      return noop;
    }
  }

  get error() {
    if (isDebugMode) {
      return console.error.bind(console);
    } else {
      return noop;
    }
  }
}
