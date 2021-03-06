/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2017-2017 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2017 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.features.topology.plugins.topo.asset.cmd;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.opennms.features.topology.plugins.topo.asset.AssetGraphMLProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>This command implements the Apache Karaf 3 and Apache Karaf 4 shell APIs.
 * Once the Karaf 4 commands work, the deprecated Karaf 3 annotations should 
 * be removed:</p>
 * <ul>
 * <li>{@link org.apache.karaf.shell.commands.Command}</li>
 * <li>{@link org.apache.karaf.shell.console.OsgiCommandSupport}</li>
 * </ul>
 */
@Command(scope = "asset-topology", name = "regenerateall", description="Uses Best Effort to regenerate all asset topologies.")
@org.apache.karaf.shell.commands.Command(scope = "asset-topology", name = "regenerateall", description="Uses Best Effort to regenerate all asset topologies.")
@Service
public class RegenerateAllAssetTopologiesCommand extends OsgiCommandSupport implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(RegenerateAllAssetTopologiesCommand.class);

	@Reference
	public AssetGraphMLProvider assetGraphMLProvider;

	@Override
	public Object execute() throws Exception {
		System.out.println("Trying to regenerate all asset topologies");
		assetGraphMLProvider.regenerateAllAssetTopologies();
		System.out.println("Regenerated all asset topologies");
		return null;
	}

	@Override
	@Deprecated
	protected Object doExecute() throws Exception {
		return execute();
	}

	@Deprecated
	public void setAssetGraphMLProvider(AssetGraphMLProvider assetGraphMLProvider) {
		this.assetGraphMLProvider = assetGraphMLProvider;
	}
}
