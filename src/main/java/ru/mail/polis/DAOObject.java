package ru.mail.polis;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DAOObject implements DAO {
  private final NavigableMap<ByteBuffer, Record> map = new TreeMap<>();

  @NotNull
  @Override
  public Iterator<Record> iterator(@NotNull ByteBuffer from) throws IOException {
    return map.tailMap(from).values().iterator();
  }

  @Override
  public void upsert(@NotNull ByteBuffer key, @NotNull ByteBuffer value) throws IOException {
    map.put(key, Record.of(key, value));
  }

  @Override
  public void remove(@NotNull ByteBuffer key) throws IOException {
    map.remove(key);
  }

  @Override
  public void close() {
    throw new UnsupportedOperationException("This operation will be implemented soon");
  }
}
