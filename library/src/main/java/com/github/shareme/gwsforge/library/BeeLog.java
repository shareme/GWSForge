package com.github.shareme.gwsforge.library;



import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * It is used to provide log history in order to show in the bee.
 */
public final class BeeLog {

  private BeeLog() {
    // no instance
  }

  private static final List<ContentHolder> LOG_HISTORY = new LinkedList<>();

  @SuppressWarnings("unused") public static void d(String tag, String message) {
    Timber.d(tag, message);
    addToHistory(tag, message);
  }

  @SuppressWarnings("unused") public static void e(String tag, String message) {
    Timber.e(tag, message);
    addToHistory(tag, message);
  }

  @SuppressWarnings("unused") public static void w(String tag, String message) {
    Timber.w(tag, message);
    addToHistory(tag, message);
  }

  @SuppressWarnings("unused") public static void i(String tag, String message) {
    Timber.i(tag, message);
    addToHistory(tag, message);
  }

  private static void addToHistory(String tag, String message) {
    LOG_HISTORY.add(new LogHistory(tag, message, new Date()));
  }

  static List<ContentHolder> getLogHistory() {
    return LOG_HISTORY;
  }

}
