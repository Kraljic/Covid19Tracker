function generateCountryInfoTable(countryConfig, countryInfoToday, countryInfoYesterday) {
    let dataPresent = false;
    let tags = "country-info ";

    if (countryInfoToday == undefined) {
        countryInfoToday = {}; // Prevent error by accessing to undefined object
    } else {
        tags += " today ";
        dataPresent = true;
    }

    if (countryInfoYesterday == undefined) {
        countryInfoYesterday = {}; // Prevent error by accessing to undefined object
    } else {
        tags += " yesterday ";
        dataPresent = true;
    }

    if (dataPresent == false) {
        tags += "no-data";
    }

    return `
    <div id="country-info">
        <table class="${tags}">
            <thead>          
                <tr>
                    <th colspan="10">
                        ${countryConfig.countryName}
                        <div class="follow-country" style="display: inline" onclick="followCountry('${countryConfig.code}')">
                            <i class="far fa-heart follow" ${(countryConfig.following && 'style="display: none;"')}></i>
                            <i class="fas fa-heart un-follow" ${(countryConfig.following || 'style="display: none;"')}></i>
                        </div>
                    </th>
                </tr>  
                <tr class="headers info-row">
                    <th> </th>
                    <th class="today info-col">Today</th>
                    <th class="yesterday info-col">Yesterday</th>
                </tr>
            </thead>
            <tbody>
                <tr class="no-data-row">
                    <th colspan="10">No data!</th>
                </tr>
                <tr class="total-cases info-row">
                    <th>Total cases:</th>
                    <td class="today number info-col">${(countryInfoToday.totalCases || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.totalCases || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="total-deaths info-row">
                    <th>Total deaths:</th>
                    <td class="today number info-col">${(countryInfoToday.totalDeaths || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.totalDeaths || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="new-cases info-row">
                    <th>New cases:</th>
                    <td class="today number info-col">${(countryInfoToday.newCases || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.newCases || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="new-deaths info-row">
                    <th>New deaths:</th>
                    <td class="today number info-col">${(countryInfoToday.newDeaths || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.newDeaths || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="total-recovered info-row">
                    <th>Total recovered:</th>
                    <td class="today number info-col">${(countryInfoToday.totalRecovered || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.totalRecovered || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="active-cases info-row">
                    <th>Active cases:</th>
                    <td class="today number info-col">${(countryInfoToday.activeCases || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.activeCases || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="serious-cases info-row">
                    <th>Serious cases:</th>
                    <td class="today number info-col">${(countryInfoToday.seriousCases || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.seriousCases || 0).toLocaleString(numberFormat)}</td>
                </tr>
                <tr class="total-cases-per-1m-population info-row">
                    <th>Tot cases/1M pop:</th>
                    <td class="today number info-col">${(countryInfoToday.totalCasesPer1MPopulation || 0).toLocaleString(numberFormat)}</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.totalCasesPer1MPopulation || 0).toLocaleString(numberFormat)}</td>
                </tr>                
                <tr class="death-percentage info-row">
                    <th>Death percentage:</th>
                    <td class="today number info-col">${(countryInfoToday.totalDeaths / (countryInfoToday.totalDeaths + countryInfoToday.totalRecovered) * 100.0).toFixed(2)}%</td>
                    <td class="yesterday number info-col">${(countryInfoYesterday.totalDeaths / (countryInfoYesterday.totalDeaths + countryInfoYesterday.totalRecovered) * 100.0).toFixed(2)}%</td>
                </tr>
            </tbody>
        </table>
    </div>
    `;
}

function generateSelectedCountriesInfo(selectedCountriesInfo) {
    let rowElements = "";

    // Sort country info by totalCases
    selectedCountriesInfo = selectedCountriesInfo.sort((c1, c2) => -(c1.totalCases - c2.totalCases));
    selectedCountriesInfo.forEach(countryInfo => {
        rowElements += createTableRow(countryInfo);
    });

    return createTable(rowElements);

    function createTable(rows) {
        return `
    <div id="info">
        <table>
            <thead>
                <tr>
                    <th>Country</th>
                    <th>Total Cases</th>
                    <th>Total Deaths</th>
                    <th>New Cases</th>
                    <th>New Deaths</th>
                </tr>
            </thead>
            <tbody>
                ${rows}
            </tbody>
        </table>
    </div>
        `;
    }

    function createTableRow(countryInfo) {
        return `
        <tr style="text-align: right;">
            <td style="width: 120px; text-align: left;">${countryInfo.country}</td>
            <td>${(countryInfo.totalCases || 0).toLocaleString(numberFormat)}</td>
            <td>${(countryInfo.totalDeaths || 0).toLocaleString(numberFormat)}</td>
            <td style="color: orange;">${(countryInfo.newCases || 0).toLocaleString(numberFormat)}</td>
            <td style="color: red;">${(countryInfo.newDeaths || 0).toLocaleString(numberFormat)}</td>
        </tr>`;
    }
}