package helloworld.service.test;

import java.io.File;
import java.util.Date;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.liferay.portal.kernel.exception.PortalException;

import helloworld.model.Foo;
import helloworld.service.FooLocalServiceUtil;

@RunWith(Arquillian.class)
public class HelloWorldServiceTest {
	

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}
	
	@Test
	public void testCreateFoo() throws PortalException  {
		Foo foo = FooLocalServiceUtil.createFoo(0);

		foo.setField1("createFooEntryField1");
		foo.setField2(true);
		foo.setField3(1);
		Date createDate = new Date();
		foo.setField4(createDate);
		foo.setField5("createFooEntryField5");
		foo.isNew();

		FooLocalServiceUtil.addFooWithoutId(foo);
				
		org.junit.Assert.assertTrue("Test Failed: Expected to see \"1\", but saw " + FooLocalServiceUtil.getFoosCount(), FooLocalServiceUtil.getFoosCount() == 1);		
	}
}
