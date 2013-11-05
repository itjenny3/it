package com.itjenny.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.rules.ExternalResource;

public class ResourceFile extends ExternalResource {
    String res;
    InputStream stream;
    InputStreamReader reader;
    BufferedReader buffer;

    @Override
    protected void before() throws Throwable {
	super.before();
	stream = getClass().getResourceAsStream(res);
	reader = new InputStreamReader(stream, Charset.forName("utf-8"));
	buffer = new BufferedReader(reader);
    }

    @Override
    protected void after() {
	IOUtils.closeQuietly(stream);
	IOUtils.closeQuietly(buffer);
	IOUtils.closeQuietly(reader);
	super.after();
    }

    public ResourceFile(String res) {
	this.res = res;
    }

    public String getContent() throws IOException {
	StringBuilder contents = new StringBuilder();
	String line;
	while ((line = buffer.readLine()) != null) {
	    contents.append(line).append('\n');
	}
	return contents.toString();
    }
}