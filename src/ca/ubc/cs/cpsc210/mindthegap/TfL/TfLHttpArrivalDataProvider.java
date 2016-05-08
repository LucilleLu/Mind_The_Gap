package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        // TODO Phase 2 Task 7
        String request = "";
        String stopPointId = stn.getID();

        List<String> lineIds = new ArrayList<>();
        for (Line line: stn.getLines()) {
            String lineId = line.getId();
            lineIds.add(lineId);
        }

        String combinedLineIds = "";
        for (String id: lineIds) {
            combinedLineIds = (combinedLineIds + id + ",");
        }

        combinedLineIds = combinedLineIds.substring(0,combinedLineIds.length()-1);

        request = ("https://api.tfl.gov.uk/Line/" + combinedLineIds + "/Arrivals?stopPointId=" + stopPointId + "&app_id=&app_key=");

        return new URL(request);
    }
}
