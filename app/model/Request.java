package model;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

import javax.inject.Inject;

import play.libs.ws.WSClient;


public class Request{
    
	@Inject
	public WSClient ws;
	
	public Request(WSClient ws) {
		this.ws = ws;
	}
	
}
