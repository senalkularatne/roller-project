import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.roller.weblogger.WebloggerException;
import org.apache.roller.weblogger.business.BootstrapException;
import org.apache.roller.weblogger.business.MultiWeblogURLStrategy;
import org.apache.roller.weblogger.business.URLStrategy;
import org.apache.roller.weblogger.business.WebloggerFactory;
import org.apache.roller.weblogger.business.startup.StartupException;
import org.apache.roller.weblogger.business.startup.WebloggerStartup;
import org.apache.roller.weblogger.pojos.Weblog;
import org.apache.roller.weblogger.pojos.WeblogEntry;
import org.apache.roller.weblogger.pojos.WeblogEntryComment;
import org.apache.roller.weblogger.pojos.WeblogEntrySearchCriteria;
import org.apache.roller.weblogger.pojos.WeblogEntryTag;
import org.apache.roller.weblogger.pojos.wrapper.WeblogEntryWrapper;
import org.apache.roller.weblogger.ui.rendering.pagers.WeblogEntriesLatestPager;
import org.apache.struts2.components.Set;
import org.junit.Before;
import org.junit.Test;

public class JUnitPractice {

	@Test// 1
	public void test1() throws WebloggerException {
		WeblogEntry weblog = new WeblogEntry();
		URLStrategy strat = null;
		
		WeblogEntryComment comment = new WeblogEntryComment();
		comment.setContent("here is a comment");
		List<WeblogEntryComment> commentList = new ArrayList<WeblogEntryComment>();
		commentList.add(comment);
		
		weblog.addTag("a");
		
		//when(WeblogEntry.getComments()).thenReturn(commentList);
		WeblogEntryWrapper weblogEntryWrapper = WeblogEntryWrapper.wrap(weblog, strat);
		List<WeblogEntryComment> alteredComments = weblogEntryWrapper.getComments();
		fail("Not yet implemented");
	}

}
