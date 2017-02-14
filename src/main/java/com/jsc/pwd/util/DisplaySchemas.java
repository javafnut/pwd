package com.jsc.pwd.util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;

public class DisplaySchemas {
	
    public static void displaySchemas(JAXBContext jc) {

        try {

            final List<DOMResult> results = new ArrayList<DOMResult>();
            SchemaOutputResolver resolver = new SchemaOutputResolver() {

                @Override
                public Result createOutput(String ns, String file)
                        throws IOException {
                    DOMResult result = new DOMResult();
                    result.setSystemId(file);
                    results.add(result);
                    return result;
                }
            };

            jc.generateSchema(resolver);

            // Display the schema's generated
            DOMResult domResult;
            Document doc;
            OutputFormat format;
            XMLSerializer serializer;
            for (int i = 0; i < results.size(); i++) {
                System.out.println("-------------------Schema number " + (i + 1));
                domResult = results.get(i);
                doc = (Document) domResult.getNode();
                format = new OutputFormat(doc);
                format.setIndenting(true);
                serializer = new XMLSerializer(System.out, format);
                serializer.serialize(doc);
            }
        } catch (Exception e) {

        }
    }
}
