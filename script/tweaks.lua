tweaks = {
	-- positioning of unit stats display
	unitstats_pos = { x = -0.2,y = 0.4, z = -3.0 },
	unitstats_pos_naval = { x = 0.0,y = 13.0, z = 1.8 },
	
	-- icons for factory types
	factory_type_pos = { x = -0.4, y = 0.4, z = -2.0 },
	factory_type_offset = { x = 0.5, y = 0, z = 0 },
	
	counter_wargoal_on_annex = { cb = "free_peoples" },
	counter_wargoal_on_install_communist_gov = { cb = "uninstall_communist_gov_cb" },
	
	flag_offsets = {
	[0] = 
	{
	-- LAND
	  [0] = { x = -0.3,		y = -0.33, 	z = 0.7 },
			{ x = -0.3, 	y = -0.58, 	z = 0.7 },
			{ x = -0.3, 	y = -0.83,	z = 0.7 },
			{ x = -0.3, 	y = -1.08, 	z = 0.7 },
			{ x = -0.3, 	y = -1.33, 	z = 0.7 },
			{ x = -0.3,	y = -1.58, 	z = 0.7 },
			{ x = -0.3, 	y = -1.83, 	z = 0.7 }
		},
	-- SEA
		{
	  [0] =	{ x = -3.5,	y = 8, 	z = 0.0 },
			{ x = -2.0, y = 8, 	z = 0.0 },
			{ x = -0.5, y = 8,	z = 0.0 },
			{ x = 1.0, 	y = 8, 	z = 0.0 },
			{ x = 2.5, 	y = 8, 	z = 0.0 },
			{ x = 4.0, 	y = 8, 	z = 0.0 },
			{ x = 5.5, 	y = 8, 	z = 0.0 }
		}
	}
}