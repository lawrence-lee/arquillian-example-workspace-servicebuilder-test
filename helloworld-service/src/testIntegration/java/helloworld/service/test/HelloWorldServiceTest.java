package helloworld.service.test;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
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

	@After
	public void tearDown() {
		List<Foo> foos = FooLocalServiceUtil.getFoos(-1, -1);

		if (!foos.isEmpty()) {
			Foo foo = foos.get(0);

			FooLocalServiceUtil.deleteFoo(foo);
		}
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

		FooLocalServiceUtil.addFoo(foo);

		Assert.assertTrue(
			"Expected to see \"1\", but saw " +
			FooLocalServiceUtil.getFoosCount() + "\"",
			FooLocalServiceUtil.getFoosCount() == 1);

	}
}
